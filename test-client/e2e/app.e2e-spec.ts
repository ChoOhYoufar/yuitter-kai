import { TestClientPage } from './app.po';

describe('test-client App', () => {
  let page: TestClientPage;

  beforeEach(() => {
    page = new TestClientPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Welcome to app!!');
  });
});
